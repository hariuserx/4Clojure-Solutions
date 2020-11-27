;(fn [v i]
;
;   (
;     loop [n i result (apply list v)]
;     (if (= n 0)
;       (first result)
;       (recur (dec n) (pop result)))
;     ))

(fn dosia [xs count] (if (= count 0) (first xs) (dosia (rest xs) (- count 1))))