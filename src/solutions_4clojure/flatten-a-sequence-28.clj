(fn [seq]
  (let [actual-size     (count seq)
        flattenx-helper (fn [i res size seq functionx] (cond (= i size) res
                                                             (or (list? (nth seq i)) (vector? (nth seq i)))
                                                             (let [nth_val (nth seq i)
                                                                   nth_res (vec (functionx 0 [] (count nth_val) nth_val functionx))]
                                                               (functionx (inc i) (vec (concat res nth_res)) size seq functionx))
                                                             :else
                                                             (functionx (inc i) (conj res (nth seq i)) size seq functionx)))]
    (flattenx-helper 0 [] actual-size seq flattenx-helper)))