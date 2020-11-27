(fn best-hand [cards]
  (let [suites       #{\H \D \C \S}
        ranks        {\2 2
                      \3 3
                      \4 4
                      \5 5
                      \6 6
                      \7 7
                      \8 8
                      \9 9
                      \T 10
                      \J 11
                      \Q 12
                      \K 13
                      \A 14}
        s            (set (map first cards))
        r            (vec (map second cards))
        fr           (frequencies r)
        _            (prn r fr s)
        seqx         (fn [x op]
                       (first (reduce (fn [[ans p] v]
                                        [(and ans
                                              (= op (- (get ranks v) (get ranks p))))
                                         v])
                                      [true (first x)]
                                      (rest x))))
        in-sequence? (if (= \A (first r))
                       (cond
                         (seqx (rest r) -1) \1
                         (seqx (rest r) +1) \2
                         :else \3)
                       (if (= \A (last r))
                         (cond
                           (seqx (pop r) +1) \1
                           (seqx (pop r) -1) \2
                           :else \3)
                         (cond
                           (or (seqx r >) (seqx r <)) \1
                           :else \3)))]
    (cond
      (and (= \1 in-sequence?) (= 1 (count s))) :straight-flush
      (and (= 2 (count fr)) (contains? (set (vals fr)) 4)) :four-of-a-kind
      (and (= 2 (count fr)) (contains? (set (vals fr)) 3)) :full-house
      (= 1 (count s)) :flush
      (or (= \1 in-sequence?) (= \2 in-sequence?)) :straight
      (and (= 3 (count fr)) (contains? (set (vals fr)) 3)) :three-of-a-kind
      (and (= 3 (count fr)) (contains? (set (vals fr)) 2)) :two-pair
      (and (= 4 (count fr))) :pair
      :else :high-card)))