(fn [a b] (let [x (mod a (count b))]
            (concat (drop x b) (take x b))))