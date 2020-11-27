(fn [x] (set
          (filter
            #(> (count %) 1)
            (vals
              (reduce (fn [acc [k v]]
                        (assoc acc
                          k
                          (clojure.set/union (get acc k) #{v})))
                      {}
                      (map #(vec
                              [(clojure.string/join ""
                                                    (sort
                                                      (clojure.string/split
                                                        %
                                                        #"")))
                               %]) x))))))