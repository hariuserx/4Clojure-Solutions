(fn [a b]
  (loop [i a
         res []]
    (if (= i b)
      res
      (recur (inc i) (conj res i)))))