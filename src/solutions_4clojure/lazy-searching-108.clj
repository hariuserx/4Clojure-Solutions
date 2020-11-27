(fn lazy-search [s1 & seqs]
  (let [lower-bound      (fn [ele coll]
                           ((fn helper [coll]
                              (cond
                                (= (first coll)
                                   ele) {
                                         :eq true :s (drop 1 coll)}
                                (> (first coll)
                                   ele) {:eq false :s coll}
                                :else
                                (helper (drop 1 coll)))) coll))
        contains-in-all? (fn [ele seqs]
                           (let [lbs (for [s seqs]
                                       (lower-bound ele s))]
                             {:cont     (every? true? (map :eq lbs))
                              :new-seqs (map :s lbs)}))]
    ((fn searcher [s1 seqs]
       (let [{new-seqs :new-seqs
              cont     :cont} (contains-in-all? (first s1) seqs)]
         (if cont
           (first s1)
           (searcher (drop 1 s1) new-seqs)))) s1 seqs)))