(fn global-take-while [n f s]
  (drop-last (:res (reduce #(if (= n (:curr %1))
                              %1
                              (if (f %2)
                                (assoc %1
                                  :curr (inc (:curr %1))
                                  :res (conj (:res %1) %2))
                                (assoc %1
                                  :res (conj (:res %1) %2))))
                           {:curr 0
                            :res  []} s))))