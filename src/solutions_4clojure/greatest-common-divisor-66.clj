(fn [x y]
  (let [a (max x y)
        b (min x y)]
    (loop [a a
           b b]
      (let [r (mod a b)]
        (if (= 0 r)
          b
          (recur b r))))))