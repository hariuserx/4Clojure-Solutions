(fn prime-sandwich? [n]
  (let [prime?         (fn [x]
                         (cond
                           (or (= x 2) (= x 3))
                           true
                           (<= x 1)
                           false
                           :else
                           ((fn helper [curr mx]
                              (if (= 0 (rem x curr))
                                false
                                (if (= curr mx)
                                  true
                                  (helper (inc curr) mx))))
                            2
                            (int (Math/sqrt x)))))
        adjacent-prime (fn [x f]
                         ((fn helper [x]
                            (if (< x 0)
                              -1
                              (if (prime? x)
                                x
                                (helper (f x)))))
                          (f x)))
        prev-prime     (adjacent-prime n dec)
        next-prime     (adjacent-prime n inc)]
    (and (prime? n) (< 0 prev-prime) (= n (/ (+ prev-prime next-prime) 2)))))