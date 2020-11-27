(fn lazy-pronouncer [arr]
  (let [pronounced ((fn pronounce [arr]
                      ((fn helper [curr c arr res]
                         (if (empty? arr)
                           (conj res c curr)
                           (if (= curr (first arr))
                             (helper curr
                                     (inc c)
                                     (drop 1 arr)
                                     res)
                             (helper (first arr)
                                     1
                                     (drop 1 arr)
                                     (conj res c curr)))))
                       (first arr)
                       1
                       (drop 1 arr)
                       [])) arr)]
    (lazy-seq (cons pronounced
                    (lazy-pronouncer pronounced)))))