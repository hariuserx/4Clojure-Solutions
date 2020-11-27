(fn [x] (let [size (count x)
              x (concat x "$")]
          (loop [i 0
                 res []]
            (if (= i size)
              res
              (recur (inc i)
                     (if (= (nth x i) (nth x (inc i)))
                       res
                       (conj res (nth x i))))))
          ))