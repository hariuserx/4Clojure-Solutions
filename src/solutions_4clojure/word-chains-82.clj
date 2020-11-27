(fn word-chain? [vals]
  (if (empty? vals)
    false
    (let [insertion?    (fn insertion? [a b]
                          (cond
                            (not= (+ (count a) 1) (count b))
                            false
                            (let [aseq (vec (seq a))
                                  bseq (vec (seq b))]
                              (loop [x aseq
                                     y bseq]
                                (if (empty? x)
                                  true
                                  (if (= -1 (.indexOf y (first x)))
                                    false
                                    (recur (rest x) (nthrest y (.indexOf y (first x))))))))
                            true
                            :else
                            false))
          substitution? (fn substitution? [a b]
                          (cond
                            (not= (count a) (count b))
                            false
                            (= a b)
                            false
                            :else
                            (let [size (count a)
                                  as   (vec (seq a))
                                  bs   (vec (seq b))]
                              (reduce #(or %1 %2) (for [i (range size)]
                                                    (= (concat (take i as) (drop (inc i) as))
                                                       (concat (take i bs) (drop (inc i) bs))))))))
          size          (count vals)
          connections   (reduce (fn [res x]
                                  (assoc res x (reduce (fn [res1 y]
                                                         (if
                                                           (or (insertion? x y)
                                                               (insertion? y x)
                                                               (substitution? x y))
                                                           (conj res1 y)
                                                           res1))
                                                       []
                                                       vals)))
                                {}
                                vals)
          fills         (reduce (fn [res x]
                                  (assoc res x false))
                                {}
                                vals)
          _             (prn fills)
          _             (prn connections)
          path-exists?  (fn path-exists? [fills counter v]
                          (prn counter fills v)
                          (let [counter (inc counter)
                                fills   (assoc fills v true)]
                            (if (= size counter)
                              true
                              (reduce #(or %1 (and (not (get fills %2))
                                                   (path-exists? fills counter %2)))
                                      false
                                      (get connections v)))))]
      (reduce #(or %1 (path-exists? fills 0 %2))
              false
              vals))))