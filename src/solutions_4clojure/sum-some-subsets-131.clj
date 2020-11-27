(fn sum-some-subsets [& sets]
  (let [subset-sums (fn [s]
                      (let [e (Math/pow 2 (count s))
                            s (vec s)]
                        (reduce (fn [res number]
                                  (conj res
                                        (:sum (reduce (fn [{:keys [n sum]} b]
                                                        (if (= \1 b)
                                                          {:n   (inc n)
                                                           :sum (+ sum (nth s n))}
                                                          {:n   (inc n)
                                                           :sum sum}))
                                                      {:n   0
                                                       :sum 0}
                                                      (reverse
                                                        (seq (Integer/toBinaryString number)))))))
                                #{}
                                (range 1 e))))]
    (not (empty? (reduce clojure.set/intersection (map subset-sums sets))))))