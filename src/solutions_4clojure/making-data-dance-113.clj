(fn data-dancer [& args] (let [sorted-args     (clojure.string/join ", " (sort args))
                               ordered-uniques (seq (:ans (reduce (fn [res val]
                                                                    (let [ans     (:ans res)
                                                                          uniques (:uniques res)]
                                                                      (if (contains? uniques val)
                                                                        {:ans     ans
                                                                         :uniques uniques}
                                                                        {:ans     (conj ans val)
                                                                         :uniques (conj uniques val)})))
                                                                  {:ans     []
                                                                   :uniques #{}}
                                                                  args)))]
                           (reify
                             Object
                             (toString [this] sorted-args)
                             clojure.lang.ISeq
                             (seq [this] ordered-uniques))))