(fn connected? [edges]
  (let [all-connections (fn [edges]
                          (reduce (fn [res [a b]]
                                    (conj res [a b] [b a]))
                                  #{}
                                  edges))
        edges           (reduce (fn [res [a b]]
                                  (assoc res a (conj (get res a) b)))
                                {}
                                (all-connections edges))
        vertices        (set (keys edges))
        dfs             (fn dfs [vertex vertices]
                          (let [vertices (disj vertices vertex)]
                            (reduce (fn [res conn]
                                      (if (contains? res conn)
                                        (dfs conn res)
                                        res))
                                    vertices
                                    (get edges vertex))))]
    (empty? (dfs (first vertices) vertices))))