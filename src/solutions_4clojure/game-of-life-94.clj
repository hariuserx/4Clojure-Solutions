(fn game-of-life [board]
  (let [board      (reduce (fn [res val]
                             (conj res (vec (seq val))))
                           []
                           board)
        rows       (count board)
        cols       (count (first board))
        neighbours (fn [i j]
                     (for [x [i (inc i) (dec i)]
                           y [j (inc j) (dec j)]
                           :when (not (and (= x i) (= y j)))]
                       (get (get board x) y)))]
    (vec (map (fn [val]
                (apply str val))
              (for [i (range rows)]
                (for [j (range cols)
                      :let [f   (frequencies (neighbours i j))
                            val (get (get board i) j)]]
                  (cond
                    (= val \#)
                    (if (or (= 2 (get f \#)) (= 3 (get f \#)))
                      \#
                      \space)
                    (= val \space)
                    (if (= 3 (get f \#))
                      \#
                      \space))))))))