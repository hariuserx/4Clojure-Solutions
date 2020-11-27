(fn [x]
  (loop [res [] s #{} i 0 size (count x)]
    (if (= size i)
      res
      (recur (if (not (contains? s (nth x i)))
               (conj res (nth x i))
               res) (conj s (nth x i)) (inc i) size))))