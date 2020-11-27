(fn  [x coll]
  (loop [res [] rem-coll coll]
    (if (> x (count rem-coll))
      res
      (recur (conj res (take x rem-coll)) (drop x rem-coll)))))