(fn brackets-balanced? [s]
  (let [brs {\} \{
             \] \[
             \) \(}
        obs (set (vals brs))
        {stack  :stack
         result :result} (reduce (fn [{:keys [result stack]} c]
                                   (if (contains? brs c)
                                     (if (= (first stack) (get brs c))
                                       {:result result
                                        :stack  (drop 1 stack)}
                                       {:result false
                                        :stack  stack})
                                     (if (contains? obs c)
                                       {:result result
                                        :stack  (conj stack c)}
                                       {:result result
                                        :stack  stack})))
                                 {:result true
                                  :stack  '()}
                                 (seq s))]
    (and result (empty? stack))))