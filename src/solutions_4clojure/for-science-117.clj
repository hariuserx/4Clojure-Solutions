(fn can-mouse-catch-cheese? [maze]
  (let [rows               (count maze)
        columns            (count (first maze))
        mouse-cheese-pos   (into (sorted-map) (for [i (range rows)
                                                    j (range columns)
                                                    :let [val (get (get maze i) j)]
                                                    :when (or (= \M val) (= \C val))]
                                                {val (+ (* columns i) j)}))
        get-row-col        (fn [index]
                             [(quot index columns) (mod index columns)])
        get-abs            (fn [r c]
                             (+ (* columns r) c))
        disjoint-sets-init (reduce (fn [disjoint-sets-init index]
                                     (let [[r c] (get-row-col index)]
                                       (if (= \# (get (get maze r) c))
                                         disjoint-sets-init
                                         (assoc disjoint-sets-init index {:root index :size 1}))))
                                   {}
                                   (range (* rows columns)))
        valid-ones         #{\space \M \C}
        get-root           (fn get-root [val disjoint-sets]
                             (if (= val (:root (get disjoint-sets val)))
                               val
                               (get-root (:root (get disjoint-sets val)) disjoint-sets)))
        unionx             (fn [x y disjoint-sets]
                             (let [root-x (get-root x disjoint-sets)
                                   root-y (get-root y disjoint-sets)
                                   size-x (:size (get disjoint-sets root-x))
                                   size-y (:size (get disjoint-sets root-y))]
                               (if (> size-x size-y)
                                 (let [disjoint-sets (assoc disjoint-sets root-y {:root root-x})
                                       disjoint-sets (assoc disjoint-sets root-x {:root root-x :size (+ size-x size-y)})]
                                   disjoint-sets)
                                 (let [disjoint-sets (assoc disjoint-sets root-x {:root root-y})
                                       disjoint-sets (assoc disjoint-sets root-y {:root root-y :size (+ size-x size-y)})]
                                   disjoint-sets))))
        disjoint-sets      (reduce (fn [disjoint-sets index]
                                     (let [[r c] (get-row-col index)]
                                       (if (= \# (get (get maze r) c))
                                         disjoint-sets
                                         (let [a             (get (get maze r) (dec c))
                                               b             (get (get maze (dec r)) c)
                                               disjoint-sets (if (contains? valid-ones a)
                                                               (unionx index (get-abs r (dec c)) disjoint-sets)
                                                               disjoint-sets)
                                               disjoint-sets (if (contains? valid-ones b)
                                                               (unionx index (get-abs (dec r) c) disjoint-sets)
                                                               disjoint-sets)]
                                           disjoint-sets))))
                                   disjoint-sets-init
                                   (range (* rows columns)))]
    (= (get-root (get mouse-cheese-pos \M) disjoint-sets) (get-root (get mouse-cheese-pos \C) disjoint-sets))))