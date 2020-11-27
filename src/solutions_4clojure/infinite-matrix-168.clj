(fn infinite-matrix
  ([f]
   (infinite-matrix f 0 0))
  ([f m n]
   (lazy-seq (cons ((fn helper-j [i j]
                      (lazy-seq (cons (f i j) (helper-j i (inc j))))) m n) (infinite-matrix f (inc m) n))))
  ([f m n s t]
   (take s (map #(take t %) (infinite-matrix f m n)))))