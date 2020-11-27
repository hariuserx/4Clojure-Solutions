(fn x [f arr]
  (if (nil? (first arr))
    nil
    (lazy-seq (cons
                (f (first arr))
                (x f (drop 1 arr))))))