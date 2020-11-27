(fn transitive-closure [s]
  (let [sm (reduce #(assoc %1 (first %2) (second %2))
                   {}
                   s)
        finder (fn finder [ans k v]
                 (if (or (= k v) (not (contains? sm v)))
                   ans
                   (finder (conj ans [k (get sm v)]) k (get sm v))))]
    (reduce (fn [res [k v]]
              (clojure.set/union (conj res [k v]) (set (finder [] k v))))
            #{}
            sm)))