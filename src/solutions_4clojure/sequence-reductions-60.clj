(fn reducex
  ([f arr]
   (reducex f
            (f (first arr))
            (drop 1 arr)))
  ([f res arr]
   (if (empty? arr)
     (lazy-seq (cons res []))
     (lazy-seq (cons res (reducex f
                                  (f res (first arr))
                                  (drop 1 arr)))))))