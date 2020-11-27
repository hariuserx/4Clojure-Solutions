(fn [n]
  (loop [res [] i 0]

    (if (= i n)
      res
      (recur (loop [builder [] j 0]

               (if (= i j)
                 (conj builder 1)
                 (recur (conj builder (+ (if (> 0 (dec j))
                                           0
                                           (nth res (dec j))) (nth res j))) (inc j)))) (inc i)))))