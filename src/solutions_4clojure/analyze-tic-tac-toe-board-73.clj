(fn get-winner [board]
  (let [row-size (count board)
        col-size (count (first board))
        check    (fn [v]
                   (cond
                     (every? #(= % :x) v) :x
                     (every? #(= % :o) v) :o))
        row-winner (reduce (fn [ans row]
                             (or ans (check row)))
                           nil
                           board)
        col-winner (reduce (fn [ans col-index]
                             (let [col (for [x (range row-size)]
                                         (get (get board x) col-index))]
                               (or ans (check col))))
                           nil
                           (range col-size))
        diag-winner (or (check (for [i (range row-size)]
                                 (get (get board i) i)))
                        (check (for [i (range row-size)]
                                 (get (get board i) (mod (- (- row-size 1) i) row-size)))))]
    (or row-winner col-winner diag-winner)))