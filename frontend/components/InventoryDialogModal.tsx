import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
} from "./ui/dialog";

interface InventoryModalProps {
  isOpen: boolean;
  onClose: () => void;
  title: string;
  description?: string;
  children: React.ReactNode;
  footer?: React.ReactNode;
  contentClassName?: string;
}

export default function InventoryDialogModal({
  isOpen,
  onClose,
  title,
  description,
  children,
  footer,
  contentClassName = "sm:max-w-[500px]",
}: InventoryModalProps) {
  return (
    <Dialog open={isOpen} onOpenChange={(open) => !open && onClose()}>
      <DialogContent
        className={`max-h-[90vh] overflow-y-auto ${contentClassName}`}
      >
        <DialogHeader>
          <DialogTitle>{title}</DialogTitle>
          {description && <DialogDescription>{description}</DialogDescription>}
        </DialogHeader>

        {children}

        {footer && <div className="flex justify-end gap-3 mt-4">{footer}</div>}
      </DialogContent>
    </Dialog>
  );
}
