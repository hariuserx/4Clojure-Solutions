(fn [a b]

  (let [size (clojure.core/min (count a) (count b))]

    (loop [i 0
           res []]
      (if (= i size)
        res
        (recur (inc i) (conj res (nth a i) (nth b i))))
      )
    )

  )