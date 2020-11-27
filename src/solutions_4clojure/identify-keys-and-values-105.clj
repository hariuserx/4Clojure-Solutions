(fn [v]
  (:result (reduce #(let [{current :current
                           result :result} %1]
                      (if (keyword? %2)
                        (assoc %1
                          :current %2
                          :result (assoc result
                                    %2
                                    []))
                        (assoc %1
                          :result (assoc result
                                    current
                                    (conj (get result current)
                                          %2)))))
                   {:current nil
                    :result {}}
                   v)))