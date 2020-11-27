;(fn [v] (
;           loop [n 0 tmp (vec v)]
;           (if (empty? tmp)
;             n
;             (recur (inc n) (pop tmp))))
;          )

#(reduce + (map (fn [x] 1) %))