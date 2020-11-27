(fn happy-number? [num]
  (let [digits-fn (fn [x]
                    (map #(Character/digit % 10)
                         (str x)))]
    ((fn helper [tracker number]
       (if (contains? tracker number)
         false
         (let [digits (digits-fn number)
               sum    (reduce #(+ %1 (* %2 %2))
                              0
                              digits)]
           (if (= 1 sum)
             true
             (helper
               (conj tracker number)
               sum))))) #{} num)))