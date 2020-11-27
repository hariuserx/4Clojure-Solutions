(fn iter [func val]
  (lazy-seq (cons val (iter func (func val)))))