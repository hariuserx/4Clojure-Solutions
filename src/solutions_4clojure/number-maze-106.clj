(fn min-path [a b]
  (if (= a b)
    1
    (loop [branches [a]
           level    2]
      (let [res (reduce (fn [res curr]
                          (let [new-branches (vec (for [x ["*" "+" "/"]
                                                        :when (or (and (= x "/") (even? curr))
                                                                  (not= x "/"))]
                                                    (cond
                                                      (= "*" x) (* 2 curr)
                                                      (= "+" x) (+ 2 curr)
                                                      (= "/" x) (/ curr 2))))]
                            (if (some #(= b %) new-branches)
                              (-> res
                                  (assoc :present true)
                                  (assoc :new-branches (concat (:new-branches res) new-branches)))
                              (assoc res :new-branches (concat (:new-branches res) new-branches)))))
                        {:present      false
                         :new-branches []}
                        branches)]
        (if (:present res)
          level
          (recur (:new-branches res) (inc level)))))))