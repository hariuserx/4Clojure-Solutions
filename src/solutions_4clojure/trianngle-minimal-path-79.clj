(fn min-path [triangle]
  (let [triangle (vec triangle)
        res      (reduce (fn [res level]
                           (conj res (vec (for [i (range (count level))]
                                            (let [prev-level (last res)
                                                  left       (get prev-level (- i 1))
                                                  right      (get prev-level i)
                                                  curr-min   (cond
                                                               (and left right)
                                                               (if (> (:min left)
                                                                      (:min right))
                                                                 right
                                                                 left)
                                                               left
                                                               left
                                                               right
                                                               right)]
                                              {:prev (:curr curr-min) :curr i :min (+ (get level i) (:min curr-min))})))))
                         [[{:prev nil :curr 0 :min (first (first triangle))}]]
                         (rest triangle))
        x        (reduce (fn [res curr]
                           (if (> (:min res)
                                  (:min curr))
                             curr
                             res))
                         (last res))
        path     (loop [ans   '()
                        curr  x
                        level (- (count res) 1)]
                   (if (nil? (:prev curr))
                     (conj ans (get (get triangle level) (:curr curr)))
                     (recur (conj ans (get (get triangle level) (:curr curr)))
                            (get (get res (- level 1)) (:prev curr))
                            (- level 1))))
        _        (prn path)]
    (:min x)))