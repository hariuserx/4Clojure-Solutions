(fn analyze-reversi [board color]
  (let [directions {:n  [-1 0]
                    :ne [-1 +1]
                    :e  [0 +1]
                    :se [+1 +1]
                    :s  [+1 0]
                    :sw [+1 -1]
                    :w  [0 -1]
                    :nw [-1 -1]}
        flips      (fn [color r c i j]
                     (loop [ans [] ri r cj c]
                       (let [ri  (+ ri i)
                             cj  (+ cj j)
                             val (get (get board ri) cj)]
                         (if (or (> ri 3)
                                 (< ri 0)
                                 (> cj 3)
                                 (< cj 0))
                           nil
                           (if (= val color)
                             nil
                             (if (= val 'e)
                               (if (empty? ans)
                                 nil
                                 [[ri cj] ans])
                               (recur (conj ans [ri cj])
                                      ri
                                      cj)))))))]
    (reduce (fn [ans row]
              (reduce (fn [ans col]
                        (if (= color (get (get board row) col))
                          (reduce (fn [ans direction]
                                    (let [f (flips color row col (first direction) (second direction))
                                          _ (prn f row col direction)]
                                      (if f
                                        (assoc ans (first f) (clojure.set/union (get ans (first f)) (set (second f))))
                                        ans)))
                                  ans
                                  (vals directions))
                          ans))
                      ans
                      (range 4)))
            {}
            (range 4))))