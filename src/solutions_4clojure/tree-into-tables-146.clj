(fn [m] (into (hash-map) (reduce (fn [res x] (concat res x)) [] (for [[k v] m]
                                                                  (for [[k1 v1] v]
                                                                    [[k k1] v1])))))