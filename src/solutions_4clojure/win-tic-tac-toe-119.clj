(fn winning-pos [piece board]
  (let [winner? (fn [board [a b]]
                  (or (every? #(= % piece) (get board a))
                      (every? #(= % piece) (for [i (range 3)]
                                             (get (get board i) b)))
                      (if (or (= b 0) (= b 2) (and (= 1 a) (= 1 b)))
                        (or (every? #(= % piece) (for [i (range 3)]
                                                   (get (get board i) i)))
                            (every? #(= % piece) (for [i (range 3)]
                                                   (get (get board i) (- 2 i)))))
                        false)))]
    (set (for [a (range 3)
               b (range 3)
               :let [val (get (get board a) b)]
               :when (and (= :e val) (winner? (assoc-in board [a b] piece) [a b]))]
           [a b]))))