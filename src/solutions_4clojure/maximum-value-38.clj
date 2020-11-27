(fn [& args] (let [size (count args)]
               (loop [x 0
                      res -10000]
                 (if (= x size)
                   res
                   (recur (inc x)
                          (if (> (nth args x) res)
                            (nth args x)
                            res))))
               ))