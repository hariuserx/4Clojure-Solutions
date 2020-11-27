(fn cross-word-solver [word board]
  (let [n                     (count word)
        board                 (reduce (fn [board row]
                                        (conj board (vec (filter #(not= \space %) row))))
                                      []
                                      board)
        rows                  (count board)
        columns               (count (first board))
        valid-edge            (fn [r c]
                                (let [val (get (get board r) c)]
                                  (or (= val nil)
                                      (= val \#))))
        present-horizontally? (fn []
                                (let [present? (fn [r c]
                                                 (reduce (fn [ans index]
                                                           (and ans (or (= \_ (get (get board r) (+ c index)))
                                                                        (= (get word index) (get (get board r) (+ c index))))))
                                                         true
                                                         (range n)))]
                                  (if (> n columns)
                                    false
                                    (loop [row 0]
                                      (if (= rows row)
                                        false
                                        (let [ans (reduce (fn [ans col]
                                                            (or ans
                                                                (and (present? row col)
                                                                     (valid-edge row (dec col))
                                                                     (valid-edge row (+ col n)))))
                                                          false
                                                          (range columns))]
                                          (if ans
                                            ans
                                            (recur (inc row)))))))))
        present-vertically?   (fn []
                                (let [present? (fn [r c]
                                                 (reduce (fn [ans index]
                                                           (and ans (or (= \_ (get (get board (+ r index)) c))
                                                                        (= (get word index) (get (get board (+ r index)) c)))))
                                                         true
                                                         (range n)))]
                                  (if (> n rows)
                                    false
                                    (loop [col 0]
                                      (if (= columns col)
                                        false
                                        (let [ans (reduce (fn [ans row]
                                                            (or ans
                                                                (and (present? row col)
                                                                     (valid-edge (dec row) col)
                                                                     (valid-edge (+ row n) col))))
                                                          false
                                                          (range rows))]
                                          (if ans
                                            ans
                                            (recur (inc col)))))))))]
    (or (present-horizontally?) (present-vertically?))))