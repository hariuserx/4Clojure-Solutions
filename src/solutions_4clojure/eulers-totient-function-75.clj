(fn eulers-totient [n]
  (reduce #(if (= 1
                  ((fn gcd [a b]
                     (let [r (rem b a)]
                       (if (= 0 r)
                         a
                         (gcd r a))))
                   %2
                   n))
             (inc %1)
             %1)
          1
          (range 2 n)))