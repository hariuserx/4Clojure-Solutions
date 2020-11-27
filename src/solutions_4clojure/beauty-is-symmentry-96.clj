#((fn symmetric? [_ l r]
    (if (or (nil? r) (nil? l))
      (if (not= l r)
        false
        true)
      (if (not= (first l) (first r))
        false
        (and (symmetric? nil (second l) (nth r 2))
             (symmetric? nil (nth l 2) (second r)))))) (first %) (second %) (nth % 2))