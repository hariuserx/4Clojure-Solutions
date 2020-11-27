(fn bt? [val]
  (if (nil? val)
    true
    (if (or (true? val) (false? val) (not= 3 (count val)) (nil? first))
      false
      (and (bt? (second val)) (bt? (nth val 2))))))