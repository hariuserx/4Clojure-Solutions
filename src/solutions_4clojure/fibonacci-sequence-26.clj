(fn [v]

  (loop [a 1
         b 1
         c v
         res [1 1]]
    (if (< c 3)
      res
      (recur b (+ a b) (- c 1) (conj res (+ a b))))

    )

  )