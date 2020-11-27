(fn [n]
  (take n
        (for [x (range)
              :when (and (> x 1)
                         (reduce #(and %1 (if (= 0 (rem x %2))
                                            false
                                            true))
                                 true
                                 (for [y (range)
                                       :when (> y 1)
                                       :while (<= (* y y) x)]
                                   y)))]
          x)))