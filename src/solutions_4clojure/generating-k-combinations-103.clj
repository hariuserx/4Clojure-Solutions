(fn k-combinations [n s]
  (if (or (= 0 n) (< (count s) n))
    #{}
    (let [s (vec s)]
      (set (map set (remove nil? (for [x (range (Math/pow 2 (count s)))]
                                   (let [bs (seq (Integer/toBinaryString x))]
                                     (if (= n (count (filter #(= %1 \1) bs)))
                                       ((fn helper [res i bs]
                                          (if (empty? bs)
                                            res
                                            (if (= \1 (first bs))
                                              (helper (conj res (nth s i))
                                                      (inc i)
                                                      (drop 1 bs))
                                              (helper res
                                                      (inc i)
                                                      (drop 1 bs)))))
                                        []
                                        0
                                        (reverse bs)))))))))))