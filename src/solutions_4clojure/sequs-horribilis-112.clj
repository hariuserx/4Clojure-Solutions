(fn seqs-horribilis [sum s]
  (:result ((fn helper [sum s]
              ((fn looper [{:keys [sum result]} s]
                 (if (empty? s)
                   {:sum    sum
                    :result result}
                   (if (sequential? (first s))
                     (let [{sum           :sum
                            result-helper :result} (helper sum (first s))]
                       (looper {:sum    sum
                                :result (if (empty? result-helper)
                                          result
                                          (conj result result-helper))}
                               (drop 1 s)))
                     (if (> (first s) sum)
                       {:sum    sum
                        :result result}
                       (looper {:sum    (- sum (first s))
                                :result (conj result (first s))}
                               (drop 1 s))))))
               {:sum    sum
                :result []}
               s))
            sum
            s)))