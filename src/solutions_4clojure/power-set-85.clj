(fn power-set [s]
  (set (reduce (fn [acc lis]
                 (concat acc (set (for [x acc
                                        y lis]
                                    (conj x y)))))
               #{#{}}
               (reduce #(conj %1 [%2])
                       []
                       s))))