
(fn insert-between [f pred s]
  (if (empty? s)
    []
    ((fn helper [s prev]
       (if (empty? s)
         [prev]
         (if (f prev (first s))
           (lazy-seq (cons prev
                           (cons pred
                                 (helper (drop 1 s)
                                         (first s)))))
           (lazy-seq (cons prev (helper (drop 1 s)
                                        (first s)))))))
     (drop 1 s) (first s))))