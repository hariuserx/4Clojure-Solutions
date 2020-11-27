(fn [elements]
  (let [size (count elements)]
    (loop [x 0
           res []]
      (if (= x size)
        res
        (recur (inc x) (concat res [(nth elements x) (nth elements x)]))))
    )
  )