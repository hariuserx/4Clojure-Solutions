(fn [x]

  (loop [i 1
         res 1]
    (if (= i x)
      (* res i)
      (recur (inc i) (* res i))
      ))

  )