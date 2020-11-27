(fn read-roman-numeral [roman]
  (let [vals {"I" 1
              "V" 5
              "X" 10
              "L" 50
              "C" 100
              "D" 500
              "M" 1000}
        size (count roman)]
    (:ans (reduce (fn [res i]
                    (let [curr (subs roman (- (- size 1) i) (- size i))
                          prev (:prev res)]
                      (if (and prev (< (get vals curr) (get vals prev)))
                        (-> res
                            (assoc :ans (- (:ans res) (get vals curr)))
                            (assoc :prev curr))
                        (-> res
                            (assoc :ans (+ (:ans res) (get vals curr)))
                            (assoc :prev curr)))))
                  {:ans 0 :prev nil}
                  (range size)))))