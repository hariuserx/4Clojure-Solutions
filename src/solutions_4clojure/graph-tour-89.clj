(fn graph-tour? [edges]
  (let [connections          (reduce (fn [connections edge]
                                       (let [a           (first edge)
                                             b           (second edge)
                                             connections (assoc connections a (concat (get connections a) [b]))]
                                         (assoc connections b (concat (get connections b) [a]))))
                                     {}
                                     edges)
        vertices-with-edges  (filter (fn [[_ v]] (> (count v) 0)) connections)
        get-visited-vertices (fn get-visited-vertices [visited-vertices [k v]]
                               (let [visited-vertices (conj visited-vertices k)]
                                 (reduce (fn [visited-vertices vertex]
                                           (get-visited-vertices visited-vertices [vertex (get connections vertex)]))
                                         visited-vertices
                                         (filter #(not (contains? visited-vertices %)) v))))
        visited-vertices     (get-visited-vertices #{} (first vertices-with-edges))
        odd-degree-vertices  (count (filter (fn [[_ v]] (odd? (count v))) connections))]
    (and (= (count visited-vertices) (count vertices-with-edges))
         (or (= 0 odd-degree-vertices)
             (= 2 odd-degree-vertices)))))