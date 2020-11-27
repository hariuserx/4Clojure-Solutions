
(fn [i] (count (filter (fn [input] (< input
                                      (apply + (map (fn [v] (* v v)) ((fn spl [x]
                                                                        (let [q (quot x 10)
                                                                              r (mod x 10)]
                                                                          (if (= 0 q)
                                                                            [r]
                                                                            (conj (spl q) r)))) input))))) i)))