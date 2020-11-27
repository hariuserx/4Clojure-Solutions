(fn perfect-number? [num]
  (= num (reduce #(if (= 0 (rem num %2))
                    (+ %1 %2)
                    %1)
                 0
                 (range 1 (+ 1 (/ num 2))))))