(fn [& args]
  (/ (apply * args) ((fn [args]
                       (reduce (fn gcd [a b]
                                 (let [x (min a b)
                                       y (max a b)
                                       r (mod y x)]
                                   (if (= 0 r)
                                     x
                                     (gcd r x)))) args)) args)))