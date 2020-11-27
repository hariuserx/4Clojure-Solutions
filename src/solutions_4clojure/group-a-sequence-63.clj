#(reduce (fn [m v]
           (let [value     (or (get m (first v)) [])
                 new-value (conj value (second v))]
             (assoc m (first v) new-value))) {}
         (for [x %2]
           [(%1 x) x]))